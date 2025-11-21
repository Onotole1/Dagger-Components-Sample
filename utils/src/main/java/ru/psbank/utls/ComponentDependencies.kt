package ru.psbank.utls

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Маркерный интерфейс для зависимостей Android компонента (Активити/Фрагмент и т.д.)
 */
interface ComponentDependencies

inline fun <reified T : ComponentDependencies> Fragment.findComponentDependencies(): T =
    findComponentDependenciesProvider()[T::class.java] as? T ?: error(
        """No component dependencies found for Fragment<${javaClass.canonicalName}>.
            | Activity is <${activity?.javaClass?.canonicalName}> with state <${activity?.lifecycle?.currentState}>.
            | Parent fragment is <${parentFragment?.javaClass?.canonicalName}>"""
            .trimMargin()
    )

inline fun <reified T : ComponentDependencies> Activity.findComponentDependencies(): T =
    findComponentDependenciesProvider()[T::class.java] as? T ?: error(
        "No component dependencies found for Activity<${javaClass.canonicalName}>"
    )

typealias ComponentDependenciesProvider =
        Map<Class<out ComponentDependencies>, @JvmSuppressWildcards ComponentDependencies>

/**
 * Родительский Android компонент, который содержит провайдеры для дочерних
 */
interface HasComponentDependencies {
    val dependencies: ComponentDependenciesProvider
}

/**
 * Ключ для связывания дочернего и родительского компонента
 */
@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ComponentDependenciesKey(val value: KClass<out ComponentDependencies>)

/**
 * Логика аналогичная AndroidSupportInjection.inject
 *
 * 1. Происходит поиск всех родительских фрагментов, которые реализуют HasComponentDependencies
 * и добавление их dependencies в список
 * 2. К списку dependencies добавляются dependencies из активити, если реализован HasComponentDependencies
 * 3. Добавляются dependencies из Application, если реализован HasComponentDependencies
 * 4. Список мёржится в Map<Class<out ComponentDependencies>, ComponentDependencies> для дальнейшего использования
 *
 * Дубли Class<out ComponentDependencies> в текущей реализации игнорируются
 * (выигрывает самый ближний к фрагменту компонент)
 */
fun Fragment.findComponentDependenciesProvider(): ComponentDependenciesProvider {
    val allHasComponentDependencies = mutableListOf<ComponentDependenciesProvider>()

    var current: Fragment? = parentFragment
    do {
        (current as? HasComponentDependencies)?.dependencies?.let(allHasComponentDependencies::add)
        current = current?.parentFragment
    } while (current != null)

    (activity as? HasComponentDependencies)?.dependencies?.let(allHasComponentDependencies::add)
    (activity?.application as? HasComponentDependencies)?.dependencies?.let(allHasComponentDependencies::add)

    val result = mutableMapOf<Class<out ComponentDependencies>, @JvmSuppressWildcards ComponentDependencies>()

    allHasComponentDependencies.reversed()
        .forEach(result::putAll)

    return result
}

fun Activity.findComponentDependenciesProvider(): ComponentDependenciesProvider {
    val hasDaggerProviders = application as? HasComponentDependencies ?: error(
        "Can not find suitable dagger provider for $this.".trimMargin()
    )

    return hasDaggerProviders.dependencies
}
