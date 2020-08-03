/**
 * @author joseph415
 */

import React from "react";
import { Text, TouchableOpacity, View } from "react-native";
import { useNavigation } from "@react-navigation/native";
import { MyPageNavigationProps } from "../types/types";

export default function MyPage() {
  const navigation = useNavigation<MyPageNavigationProps>();

  return (
    <View style={{ flex: 1 }}>
      <TouchableOpacity
        style={{ flex: 7 }}
        onPress={() => navigation.navigate("SalesDetails")}
      >
        <Text>판매 목록</Text>
      </TouchableOpacity>
    </View>
  );
}
