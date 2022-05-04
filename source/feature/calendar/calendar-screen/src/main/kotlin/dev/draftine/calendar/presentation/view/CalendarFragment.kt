package dev.draftine.calendar.presentation.view

import dev.draftine.arch.presentation.fragment.BaseFragment
import dev.draftine.arch.presentation.fragment.BottomNavigationFragment
import dev.draftine.calendar.R
import dev.draftine.calendar.presentation.viewmodel.CalendarViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalendarFragment :
    BaseFragment<CalendarViewModel>(R.layout.calendar_fragment),
    BottomNavigationFragment {

    override val viewModel: CalendarViewModel by viewModel()
}