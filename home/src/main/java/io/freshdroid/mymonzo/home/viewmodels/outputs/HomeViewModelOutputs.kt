package io.freshdroid.mymonzo.home.viewmodels.outputs

import io.freshdroid.mymonzo.home.views.FragmentState
import io.reactivex.Observable

interface HomeViewModelOutputs {

    fun displaySelectedFragment(): Observable<FragmentState>

    fun initBottomNavigationBar(): Observable<FragmentState>

}