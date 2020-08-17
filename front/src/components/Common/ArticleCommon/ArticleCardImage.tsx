import React from "react";
import { Image, StyleSheet, View } from "react-native";

interface IArticleCardImage {
  thumbnail: string;
}

export default function ArticleCardImage({ thumbnail }: IArticleCardImage) {
  return (
    <View style={styles.imageContainer}>
      <Image style={styles.image} source={{ uri: thumbnail }} />
    </View>
  );
}

const styles = StyleSheet.create({
  imageContainer: {
    flex: 4.5,
    margin: 13,
  },
  image: {
    width: "100%",
    height: "100%",
    resizeMode: "contain",
    borderRadius: 15,
  },
});
