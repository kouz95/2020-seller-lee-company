import React from "react";
import { StyleSheet, Text, View } from "react-native";
import { useRecoilState } from "recoil/dist";
import { useNavigation } from "@react-navigation/native";
import { articleSelectedCategoryState } from "../../states/articleState";
import theme from "../../colors";

interface CategoryItemProps {
  title: string;
}

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
    <View style={styles.container}>
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
  container: {
    backgroundColor: "#fff",
    padding: 15,
    borderBottomWidth: 0.2,
    borderBottomColor: "#BBB",
  },
  selected: {
    color: theme.primary,
  },
});
