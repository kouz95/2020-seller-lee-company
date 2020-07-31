/**
 * @author begaonnuri
 */

import React from "react";
import { StyleSheet, View } from "react-native";
import ArticleDetailTitle from "./ArticleDetailTitle";
import CategoryAndTime from "./CategoryAndTime";
import ArticleDetailContents from "./ArticleDetailContents";
import FavoriteCountAndHit from "./FavoriteCountAndHit";

export default function ArticleDetail() {
  return (
    <View style={styles.container}>
      <View style={styles.titleContainer}>
        <ArticleDetailTitle />
      </View>
      <View style={styles.subtitleContainer}>
        <CategoryAndTime category={"디지털/가전"} time={"1분 전"} />
      </View>
      <View style={styles.contentsContainer}>
        <ArticleDetailContents />
      </View>
      <View style={styles.subtitleContainer}>
        <FavoriteCountAndHit favoriteCount={5} hit={64} />
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