package com.anthony.charts.di.components

import android.content.Context
import com.anthony.charts.di.modules.ApplicationModule
import com.anthony.charts.di.modules.ChartsModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class
    ]
)
interface ApplicationComponent {
    fun createChartsSubcomponent(module: ChartsModule): ChartsSubcomponent

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setContext(context: Context): Builder

        fun build(): ApplicationComponent
    }
}