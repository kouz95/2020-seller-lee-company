/**
 * @author joseph415
 */

import React from "react";
import { FlatList } from "react-native";
import { categoryMockArticles } from "../data/categoryArticleMockData";
import SalesDetailsComponent from "../components/SalesDetailsComponent";

export default function SalesDetailsScreen() {
  return (
    <FlatList
      data={categoryMockArticles}
      renderItem={({ item }) => (
        <SalesDetailsComponent
          title={item.title}
          price={item.price}
          createdAt={item.createdAt}
          detail={item.detail}
          additional={item.additional}
          thumbnail={item.thumbnail}
        />
      )}
      keyExtractor={(item, index) => `${index}`}
    />
  );
}
