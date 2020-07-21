/**
 * @author begaonnuri
 */

import React from "react";
import { StyleSheet, Text, View } from "react-native";
import { CategoryItemProps } from "../types/types";
import { useRecoilState } from "recoil/dist";
import { useNavigation } from "@react-navigation/native";
import { articleSelectedCategoryState } from "../states/articleState";

export default function CategoryItem({ title }: CategoryItemProps) {
  const navigation = useNavigation();
  const [selectedCategory, setSelectedCategory] = useRecoilState(
    articleSelectedCategoryState,
  );

  const onClickCategory = () => {
    setSelectedCategory(title);
    navigation.goBack();
  };

  return (
    <View style={styles.writeButtonContainer}>
      <Text
        style={selectedCategory === title ? styles.selected : {}}
        onPress={onClickCategory}
      >
        {title}
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  writeButtonContainer: {
    backgroundColor: "#fff",
    padding: 15,
    borderBottomWidth: 0.2,
    borderBottomColor: "#BBB",
  },
  selected: {
    color: "coral",
  },
});
