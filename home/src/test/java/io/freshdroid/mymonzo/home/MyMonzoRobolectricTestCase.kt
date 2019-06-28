package io.freshdroid.mymonzo.home

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.android.lifecycle.test.TestLifecycleOwner
import io.freshdroid.mymonzo.core.di.CoreComponent
import junit.framework.TestCase
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = MyMonzoApplicationTest::class)
internal abstract class MyMonzoRobolectricTestCase : TestCase() {

    private var _application: MyMonzoApplicationTest? = null

    protected fun application(): MyMonzoApplicationTest {
        if (_application != null) {
            return _application as MyMonzoApplicationTest
        }

        _application = ApplicationProvider.getApplicationContext() as MyMonzoApplicationTest
        return _application as MyMonzoApplicationTest
    }

    protected fun context(): Context {
        return application().applicationContext
    }

    protected fun coreComponent(): CoreComponent {
        return application().coreComponent()
    }

    protected fun scopeProvider(): AndroidLifecycleScopeProvider {
        val testLifecycleOwner = TestLifecycleOwner.create()
        return AndroidLifecycleScopeProvider.from(testLifecycleOwner)
    }

}