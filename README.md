# navigation
Testing Android Navigation Componenet

Dependencies
```
implementation 'androidx.navigation:navigation-fragment-ktx:2.3.5'
implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'
```    
Dependencies for testing navigation
```
androidTestImplementation("androidx.navigation:navigation-testing:2.3.5")
```

Navigation occurs between `destinations` which are connected via `actions`. A `navigation graph` is a `resource` file that contains all the `actions` and `destinations`.
The grpah represents all the app's navigation paths. In a navigation graph, the destination is represented via `preview thumbnail` whereas the action is represented via `arrows`.

> Add a navigation graph
- Right click on `res`
- `New` > `Resource file`
- Select `Navigation` and add file

A navigation graph will be placed inside `res`/`navigation`

`<navigation>` is the root element of the xml graph file. All the `destinations` and `actions` are added as child element of `<navigation>` in the navigation xml file. If there are
`nested graphs`, they appear as `child` `<navigation>` elements.
```xml
<navigation
            id = "@+id/my_nav">
            
            <destination>
                        <action>
                        </action>
            </destination>
            
            <destination>
                        <navigation>
                        </navigation>
            </destination>
<navigation>
```

The root of the navigation graph has id.

Navigation graph can be edited via `Navigation Editor` or xml code.
The `Navigation Editor` has three components:
- Destination Panel : Contains `Navigation Host` and `destinations`
- Graph Editor : To edit navigation graphs visually
- Attributes: Attributed for currently selected item in the navigation graph

> Add a `NavHost` to an `activity`
A navigation host is an `empty container` where `destinations` are swapped in and out as user navigates through the app. It derives from `NavHost`. `NavHostFragment` is the default implementation of `NavHost`.
Ideally: Actvity > Navigation Graph > NavHostFragment
That is, an activity is associated with one navigation graph and contains a NavHostFragment. In case of multiple activities, there are multiple navigation graphs.

Inside the activity's xml, add `NavHostFragment` : 

```xml
<FrameLayout>
            
            <FragmentContainerView
                                   id="@+id/navHostFragment"
                                   name="androidx.navigation.fragment.NavHostFragment"
                                   app:defaultNavHost="true"
                                   app:navGraph="@navigation/nav_graph"
                                   />
            
</FrameLayout>
```
- The `name` attribute contains name of `NavHost` implementation class
- The `app:navGraph` attribute associates this `NavHost` fragment with a navigation graph - the graph contains all possible destination in this `NavigationFragment`
- The `app:defaultNavHost` attribute makes sure back press is handled. Only one `navHost` can be default and should be specified as such even in cases of multiple navigation fragments such as in Master Detail View.
- Lastly, the `id` attribute helps to recognize navigation host inside the navigation grpah editor

There are two ways to create a navigation host and associate that with a navigation graph.
1. Via xml file - Add a `<FragmentContainerView>` with following properties to make relationship between a `NavHostFragment` and navigation graph
```xml
name="androidx.navigation.fragment.NavHostFragment"
app:navGraph="@navigation/nav_graph_here"
app:defaultNavHost="true"
```
2. Via Layout Editor - Add a `NavHostFragment` component to the UI, a dialog box appears allowing you to select a navigation graph resource to associate it with. Directly adding a `NavHostFragment` simply adds a `<FragmentContainerView>` with above three attributes

---

> Add `destinations` in the navigation graph

There are two ways to add a destination.
1. Select the `Add desination` icon from the Graph Editor, select any fragment destination, a `placeholder` desination can alo be selected that will add following destination:
```xml
<fragment id="@+id/placeholder1"/>
```
Remember the `id` field is used to recognize it in the Graph Editor, and most importantly it is used to recognize destination in the code.
2. Simply create a `<fragment>` child in the navigation graph root element `<navigation>` - with following properties:
```xml
<navigation>
            <fragment id="@+id/thisIsNotAPlaceholderDestination"
                      name="this.is.the.full.package.name.of.the.fragment.i.want.as.destination.here.so.MyDestinationFragment"
                      label="thisIsLabelForMyDestinationActivity"/>
</navigation>
```
The `name` attribute is used to associate this destination with a particular Fragment in the app. The destination can also be an Activity as below - and here the name field will be used to associate the Activity with the destination.
```xml
<navigation>
            <activity id="@+id/thisIsNotAPlaceholderDestination"
                      name="this.is.the.full.package.name.of.the.ACTIVITY.i.want.as.destination.here.so.MyDestinationActivity"
                      label="thisIsLabelForMyDestinationActivity"/>
</navigation>
```
The label is used in Graph Editor to recognize the destination, and also may `surface` in the UI when `setupWithNavController()` is used.
All these information are in the Graph Editor as well. Also there is a `class` dropdown option in the Graph Editor that recognises as well as helps to change the class associated with the destination - Eg. From a fragment class to activity class or any other custom class.

---
> Designate a screen as the start screen

The start screen is `the first` screen user sees when the app/activity starts and `the last` screen when the app/activity closes.
Setting a screen as start screen can be done via Graph Editor with the `Home` icon.
Or, this can also be done by adding `app:startDestination="@id/placeholder1"` attribute in the root view of the navigation graph as:
```xml
<navigation
            app:startDestination="@id/placeholder1">
</navigation>
```
---

> Add actions to connect destinations

`Actions` are the `logical connections` between destinations - represented by `arrows` in the Navigation Graph.
Though actions connect one destination to another, they can also be used to go to `specific destination` from `anywhere in the app` using `Global Actions`.
Though most things visual, in order to actually navigate to a destination actual code has to be written.

To add an action simply use the Graph Editor and connect two destinations. Doing that adds `<action>` child inside the `start destination` as:
```xml
 <fragment android:id="@+id/placeholder1" >
        <action
            android:id="@+id/action_placeholder1_to_placeholder2"
            app:destination="@id/placeholder2" />
    </fragment>
```

Here, the `placeholder` destination has an `action` with `id` `action_placeholder1_to_placeholder2` that leads to destination `placeholder2`, - which is simply the `id` of the destination class.
So in a nutshell, this can be read as `this start destination with this destination id to that end destination with that destination id via this action with this action id`.
At minimun, an action has the `id` of itself, and the `destination id` that it leads to.

---

> Navigate to a destination

Navigation to the destination is done via code using `NavController`, an object that manages app navigation within a `NavHost`. This also means, if there are multiple navigation hosts, then there will also be multiple NavController. For every Navigation Host, there is an exact one `NavController` object.
The `NavController` object can be retrieved with:
```kotlin

    Fragment.findNavController()
    View.findNavController()
    Activity.findNavController(viewId: Int)
```



Also, navigation host fragment created with `FragmentContainerView` or  where navigation host fragment is added manually via fragment transaction, then trying to access navigation controller via ` Activity.findNavController(viewId: Int)` on Activity's `onCreate` will fail. In such case navigation controller should be accessed via `NavHostFragment` as:
```
val navHostFragment =
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
val navController = navHostFragment.navController
```
---

> Ensuring type-safety with safe args

It's recommended to use the Safe Args gradle plugin to navigate between destinations. This plugin ensures type safety by generating simple objects and builder classes.

To include `safe args` gradle plugin, simply add the following dependency
```
  classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
```
Also, to generate the code, one of the following plugins must be used as well:
For both Java and Kotlin:
```
plugins {
    id("androidx.navigation.safeargs")
}
```
For Kotlin only
```
plugins {
    id("androidx.navigation.safeargs.kotlin")
}
```

After the plugin is enabled, it generates `classes` and `methods` for each action we've defined. 


