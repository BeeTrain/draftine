package dev.draftine.ui.extension

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FragmentArgumentDelegate<T : Any> : ReadWriteProperty<Fragment, T> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val key = property.name
        return thisRef.arguments
            ?.get(key) as? T
            ?: throw IllegalStateException("Property ${property.name} could not be read")
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        val args = thisRef.arguments
            ?: Bundle().also(thisRef::setArguments)
        val key = property.name
        args.put(key, value)
    }
}

fun <T : Any> argument(): ReadWriteProperty<Fragment, T> = FragmentArgumentDelegate()

class FragmentNullableArgumentDelegate<T : Any?> : ReadWriteProperty<Fragment, T?> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T? {
        val key = property.name
        return thisRef.arguments?.get(key) as? T
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T?) {
        val args = thisRef.arguments
            ?: Bundle().also(thisRef::setArguments)
        val key = property.name
        value?.let { args.put(key, it) } ?: args.remove(key)
    }
}

fun <T : Any> argumentNullable(): ReadWriteProperty<Fragment, T?> {
    return FragmentNullableArgumentDelegate()
}

class FragmentFindViewDelegate<T : View>(private val id: Int) : ReadOnlyProperty<Fragment, T> {
    private var value: T? = null
    private val lifecycleObserver = PropertyLifecycleObserver()

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val view = thisRef.view ?: throw IllegalStateException("View could not be null")

        var currentValue = value
        return if (currentValue == null) {
            lifecycleObserver.attach(thisRef.lifecycle)
            currentValue = view.findViewById(id)
            value = currentValue

            currentValue
        } else {
            currentValue
        }
    }

    private inner class PropertyLifecycleObserver : DefaultLifecycleObserver {

        private var _lifecycle: Lifecycle? = null

        fun attach(lifecycle: Lifecycle) {
            _lifecycle = lifecycle
            _lifecycle?.addObserver(this)
        }

        override fun onPause(owner: LifecycleOwner) {
            removeObserver()
            super.onPause(owner)
        }

        override fun onStop(owner: LifecycleOwner) {
            removeObserver()
            super.onStop(owner)
        }

        override fun onDestroy(owner: LifecycleOwner) {
            removeObserver()
            super.onDestroy(owner)
        }

        private fun removeObserver() {
            value = null
            _lifecycle?.removeObserver(this)
            _lifecycle = null
        }
    }
}

fun <T : View> findView(id: Int): ReadOnlyProperty<Fragment, T> {
    return FragmentFindViewDelegate(id)
}

fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    context?.toast(text, duration)
}

fun Fragment.enableTouch() {
    activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Fragment.disableTouch() {
    activity?.window?.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}