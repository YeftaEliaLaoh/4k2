package com.example.a4k.android.di

import com.example.a4k.android.SampleApp
import com.example.a4k.core.di.CoreComponent
import com.example.a4k.core.di.scopes.AppScope
import dagger.Component

/**
 * App component that application component's components depend on.
 *
 * @see Component
 */
@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {

    /**
     * Inject dependencies on application.
     *
     * @param application The sample application.
     */
    fun inject(application: SampleApp)
}
