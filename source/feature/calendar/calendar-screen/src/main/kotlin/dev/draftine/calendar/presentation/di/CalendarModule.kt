package dev.draftine.calendar.presentation.di

import dev.draftine.calendar.presentation.viewmodel.CalendarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val calendarModule = module {

    viewModel { CalendarViewModel() }
}