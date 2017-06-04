# Easychange Welcome Library
**Easychange Welcome Library** is a simple android library written in kotlin that allows show a fancy welcome in your app with fews (and easy) configurations. 

# Sample
![alt text](https://github.com/flopezluksenberg/easychange-app-welcome/blob/develop/sample.gif?raw=true "Sample Video")

# Configuration
To configure your welcome intro you need follow next steps:
1. Create an android application class in your app if you don't have one yet
2. In onCreate method of your application class, initialize welcome library with welcome steps knowing that welcome step definition is like next code:

**WelcomeStep definition**
```
data class WelcomeStep(val title: String,
                       val description: String,
                       val backgroundColor: Int,
                       val imageResourceId: Int
```

Example of WelcomeStep can be:
```
WelcomeStep(
    "A very nice title for this tab!",
    "A very nice description in this tab,
    ContextCompat.getColor(this, android.R.color.holo_red_dark),
    R.drawable.an_icon_in_the_middle_of_screen
)
```

**Application class sample**
```
class YourApplicationClass : Application(){
    private val welcomeSteps : Array<WelcomeStep> = arrayOf(WelcomeStep(...), WelcomeStep(...), ...)
    //...
    override fun onCreate() {
        WelcomeApi.init(this, welcomeSteps)
    }
    //...
}
```
3. (Optional) Set welcome activity theme. To perform this you must add next code in your app AndroidManifest.xml
```
<application ...>
    //..
    <activity android:name="com.easychange.welcome.ui.WelcomeActivity" android:theme="MyVeryFancyTheme!"/>
    //..
</application>
```
**Note**: It is recommended to use theme  that inherits from some variant of NoActionBar theme
