package br.com.tramalho.enjoeitest.infraestructure

import android.annotation.SuppressLint
import android.databinding.BindingAdapter
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.Log

@SuppressLint("RestrictedApi")
fun BottomNavigationView.disableShiftMode() {
    val menuView = getChildAt(0) as BottomNavigationMenuView
    try {
        val shiftingMode = menuView::class.java.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false
        for (i in 0 until menuView.childCount) {
            val item = menuView.getChildAt(i) as BottomNavigationItemView
            item.setShiftingMode(false)
            // set once again checked value, so view will be updated
            item.setChecked(item.itemData.isChecked)
        }
    } catch (e: NoSuchFieldException) {
        Log.e(this.javaClass.simpleName, "Unable to get shift mode field", e)
    } catch (e: IllegalStateException) {
        Log.e(this.javaClass.simpleName, "Unable to change value of shift mode", e)
    }
}

private fun BottomNavigationView.log(e: Exception) {
    Log.e(this.javaClass.simpleName, "removeShiftMode > Unable to get shift mode field", e);
}


