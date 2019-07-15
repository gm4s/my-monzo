# MyMonzo for Android™

[![Kotlin](https://img.shields.io/badge/kotlin-1.3.41-519EF8.svg)](https://kotlinlang.org/) [![Java](https://img.shields.io/badge/java-1.8-5C819D.svg)](https://developer.android.com/guide/index.html)
[![Min SDK](https://img.shields.io/badge/min%20SDK-21-lightgrey.svg)](http://developer.android.com/about/dashboards/index.html#Platform)

## Modular Architecture :wrench:

Never heard about modular architecture? click [here](https://www.youtube.com/watch?v=PZBg5DIzNww)

For each new features please create its own `component`

```kotlin
@NewFeatureScope
@Component(
        dependencies = [CoreComponent::class],
        modules = [NewFeatureModule::class]
)
interface NewFeatureComponent {

    fun environment(): NewFeatureEnvironment

}
```

Its own `environment`

```kotlin
data class NewFeatureEnvironment @Inject constructor(
       // Add properties needed. Ex: api, analytics, scheduler, etc.
)
```

And its own `module`
```kotlin
@Module
class NewFeatureModule {

    @Provides
    @NewFeatureScope
    fun provideNewFeatureEnvironment(): NewFeatureEnvironment {
        return NewFeatureEnvironment()
    }

}
```

## Design Pattern :zap:

[MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) facilitates a separation of development of the graphical user interface from development of the business logic or back-end logic (the data model). The view model of MVVM is a value converter, meaning the view model is responsible for exposing (converting) the data objects from the model in such a way that objects are easily managed and presented. In this respect, the view model is more model than view, and handles most if not all of the view's display logic. The view model may implement a mediator pattern, organizing access to the back-end logic around the set of use cases supported by the view.

For each new `ViewModel`'s please follow this implementation:

```kotlin
class NewFeatureViewModel(
        environment: NewFeatureEnvironment,
        scopeProvider: AndroidLifecycleScopeProvider
): ActivityViewModel() {

    class Factory(
            private val _environment: NewFeatureEnvironment,
            private val _scopeProvider: AndroidLifecycleScopeProvider
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NewFeatureViewModel(_environment, _scopeProvider) as T
        }
    }

}
```

To bind the `ViewModel` to the activity:

```kotlin
private val viewModelFactory by lazy {
    NewFeatureViewModel.Factory(component.environment(), scopeProvider)
}

private val viewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory).get(NewFeatureViewModel::class.java)
}
```

## Gradle :elephant:

Test a unique class
```
./gradlew :${MODULE}:testReleaseUnitTest --tests ${CLASS REFERENCE} --info
```

Test all
```
./gradlew :${MODULE}:testReleaseUnitTest
```

Lint
```
./gradlew :${MODULE}:lintRelease
```

## Git :floppy_disk:
### Branch naming convention

```
$ git branch -b [developer-initial]/[issue-tag-number]_[issue-name]
```

### Formatting commit messages
```
$ git commit -am "[... your message ...]"
```
*All commit message line will be cropped at 100 characters*

### Formatting pull request

Please follow the template [PULL_REQUEST_TEMPLATE](https://github.com/gm4s/my-monzo/blob/master/.github/PULL_REQUEST_TEMPLATE.md) when you create a new pull request.

## Android Version Support :iphone:

Android fragmentation analytics --> [Platform Versions](http://developer.android.com/about/dashboards/index.html#Platform)

* Min API 21 --> Lolipop : 5.0.x
* Max API 28 --> Oreo : 8.1.x

## Android Code Style Convention :watermelon:

* To see the Android docs --> [Click here](https://source.android.com/source/code-style)
* To see the Kotlin docs --> [Click here](https://kotlinlang.org/docs/reference/coding-conventions.html)

## Cookbooks :books:

* Modular Architecture:
  - https://medium.com/mindorks/writing-a-modular-project-on-android-304f3b09cb37,
  - https://medium.com/androiddevelopers/a-patchwork-plaid-monolith-to-modularized-app-60235d9f212e
  - https://medium.com/mindorks/dynamic-feature-modules-the-future-4bee124c0f1
  - https://www.youtube.com/watch?v=PZBg5DIzNww
  - https://github.com/android/plaid
* Reactive Functional MVVM:  
  - https://github.com/kickstarter/android-oss
* Server-Driven Rendering:
  - https://medium.com/airbnb-engineering/whats-next-for-mobile-at-airbnb-5e71618576ab
  - https://youtu.be/-A8EJ8_eVOY?t=8m25s

## Contributors :cookie:

[Guillaume Mas](https://github.com/NodensN)
