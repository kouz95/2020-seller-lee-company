import React from "react";
import { StyleSheet, View } from "react-native";
import ArticleDetailTitle from "./ArticleDetailTitle";
import CategoryAndTime from "../Common/Info/CategoryAndTime";
import ArticleDetailContents from "./ArticleDetailContents";
import FavoriteCountAndHit from "../Common/Info/FavoriteCountAndHit";

export default function ArticleDetail() {
  return (
    <View style={styles.container}>
      <View style={styles.titleContainer}>
        <ArticleDetailTitle />
      </View>
      <View style={styles.subtitleContainer}>
        <CategoryAndTime />
      </View>
      <View style={styles.contentsContainer}>
        <ArticleDetailContents />
      </View>
      <View style={styles.subtitleContainer}>
        <FavoriteCountAndHit />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  titleContainer: {
    paddingHorizontal: 1,
    marginTop: 10,
  },
  subtitleContainer: {
    marginVertical: 15,
  },
  contentsContainer: {
    marginTop: 5,
  },
});
