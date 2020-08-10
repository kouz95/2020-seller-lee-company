/**
 * @author joseph415
 */

import React, { useEffect, useState } from "react";
import { FlatList } from "react-native";
import OnSaleAndReservationComponent from "../components/OnSaleAndReservationComponent";
import { MiniArticleCardProps } from "../types/types";
import axios from "axios";

export default function SalesDetailsScreen() {
  const [salesDetails, setSalesDetails] = useState<MiniArticleCardProps[]>();

  const getSalesDetails = async () => {
    const { data } = await axios.get(
      "http://localhost:8080/articles/tradeState",
      {
        params: {
          tradeState: "예약중|판매중",
        },
      },
    );
    setSalesDetails([...data]);
  };

  useEffect(() => {
    getSalesDetails().catch((reason) => console.warn(reason));
  }, []);

  return (
    <FlatList
      data={salesDetails}
      renderItem={({ item }) => (
        <OnSaleAndReservationComponent
          id={item.id}
          title={item.title}
          price={item.price}
          tradeType={item.tradeType}
          location={item.location}
          createdTime={item.createdTime}
          favoriteCount={item.favoriteCount}
          chatCount={item.chatCount}
          thumbnail={item.thumbnail}
          tradeState={item.tradeState}
        />
      )}
      keyExtractor={(item, index) => `${index}`}
    />
  );
}
