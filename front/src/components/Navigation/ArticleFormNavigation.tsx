import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import ArticleFormScreen from "../../screens/ArticleFormScreen";
import ArticleContentsFormScreen from "../../screens/ArticleContentsFormScreen";
import CategoryChoiceScreen from "../../screens/CategoryChoiceScreen";

const Stack = createStackNavigator();

export default function ArticleFormNavigation() {
  return (
    <Stack.Navigator initialRouteName="ArticleFormScreen">
      <Stack.Screen name="ArticleFormScreen" component={ArticleFormScreen} />
      <Stack.Screen
        name="ArticleContentsFormScreen"
        component={ArticleContentsFormScreen}
      />
      <Stack.Screen
        name="CategoryChoiceScreen"
        component={CategoryChoiceScreen}
      />
    </Stack.Navigator>
  );
}
