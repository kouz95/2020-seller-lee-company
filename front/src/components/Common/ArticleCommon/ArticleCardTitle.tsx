/**
 * @author joseph415
 */

import React from "react";
import { StyleSheet, Text, View } from "react-native";

interface IArticleCardTitle {
  title: string;
}

export default function ArticleCardTitle({ title }: IArticleCardTitle) {
  const MAX_LIMIT_TITLE_LENGTH = 47;

  return (
    <View style={styles.titleContainer}>
      <Text style={styles.title}>
        {title.length > MAX_LIMIT_TITLE_LENGTH
          ? title.substring(0, MAX_LIMIT_TITLE_LENGTH - 3) + "..."
          : title}
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  titleContainer: {
    flex: 2,
    flexWrap: "wrap",
    alignItems: "flex-start",
  },
  title: {
    margin: 3,
    fontSize: 14,
    fontWeight: "bold",
  },
});
