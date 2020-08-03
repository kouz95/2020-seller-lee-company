/**
 * @author joseph415
 */

import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import MyPage from "../../screens/MyPage";
import SalesDetailsScreen from "../../screens/SalesDetailsScreen";
import { MyPageParamList } from "../../types/types";

const Stack = createStackNavigator<MyPageParamList>();

export default function MyPageNavigation() {
  return (
    <Stack.Navigator>
      <Stack.Screen name="MyPage" component={MyPage} />
      <Stack.Screen name="SalesDetails" component={SalesDetailsScreen} />
    </Stack.Navigator>
  );
}
