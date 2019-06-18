package io.freshdroid.core.preferences

interface LongPreferenceType {

    /**
     * Get the current value of the preference.
     */
    fun get(): Long

    /**
     * Returns whether a value has been explicitly set for the preference.
     */
    fun isSet(): Boolean

    /**
     * Set the preference to a value.
     */
    fun set(value: Long)

    /**
     * Delete the currently stored preference.
     */
    fun delete()

}