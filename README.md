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
> A navigation graph will be placed inside `res`/`navigation`

`<navigation>` is the root element of the xml graph file. All the `destinations` and `actions` are added as child element of `<navigation>` in the navigation xml file. If there are
`nested graphs`, they appear as `child` `<navigation>` elements.
```
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
