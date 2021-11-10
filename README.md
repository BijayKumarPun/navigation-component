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
That is, an activity is associated with one navigation graph and contains a NavHostFragment. In case of multiple activities, ther eare multiple navigation graphs.

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

