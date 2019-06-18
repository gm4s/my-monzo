package io.freshdroid.mymonzo.navigation

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity

object IntentBuilder {

    @JvmStatic
    fun build(uri: Uri): Intent {
        return UriResolver.resolve(uri)
    }

    @JvmStatic
    fun build(parentUri: Uri, childUri: Uri?): Array<Intent> {
        val intents = ArrayList<Intent>()
        intents.add(
            UriResolver.resolve(parentUri).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        )
        childUri?.let {
            intents.add(
                UriResolver.resolve(childUri)
            )
        }
        return intents.toTypedArray()
    }

    @JvmStatic
    fun buildGallery(): Intent {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).setType("image/*")
        return Intent.createChooser(intent, "Choose your picture") // TODO replace hardcoded text
    }

    @JvmStatic
    fun buildCamera(): Intent {
        return Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    }

    @JvmStatic
    fun buildPermissionSettings(activity: AppCompatActivity): Intent {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", activity.packageName, null)
        intent.data = uri
        return intent
    }

    @JvmStatic
    fun buildNotificationsSettings(activity: AppCompatActivity): Intent {
        val intent = Intent()
        when {
            android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1 -> {
                intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
                intent.putExtra("android.provider.extra.APP_PACKAGE", activity.packageName)
            }
            android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
                intent.putExtra("app_package", activity.packageName)
                intent.putExtra("app_uid", activity.applicationInfo.uid)
            }
            else -> {
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.data = Uri.parse("package:${activity.packageName}")
            }
        }
        return intent
    }

    @JvmStatic
    fun buildGooglePlay(activity: AppCompatActivity): Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${activity.packageName}"))
    }

}