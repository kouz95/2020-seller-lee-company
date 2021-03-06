import { StackNavigationProp } from "@react-navigation/stack";

export type ArticleNavigationParamList = {
  FeedHomeScreen: undefined;
  ArticleDetailScreen: undefined;
  ArticleDetailImageSlider: undefined;
  ArticleDetailImageViewScreen: undefined;
  ArticleFormScreen: undefined;
  ArticleContentsFormScreen: undefined;
  CategoryChoiceScreen: undefined;
  TeaserScreen: undefined;
};

export type FeedHomeNavigationProp = StackNavigationProp<
  ArticleNavigationParamList,
  "FeedHomeScreen"
>;

// export type ChatRoomNavigationProp = StackNavigationProp<
//   ArticleNavigationParamList,
//   "ChatRoom"
// >;

export type ArticleDetailNavigationProp = StackNavigationProp<
  ArticleNavigationParamList,
  "ArticleDetailScreen"
>;

export type ArticleDetailImageSliderNavigationProp = StackNavigationProp<
  ArticleNavigationParamList,
  "ArticleDetailImageSlider"
>;

export type ArticleDetailImageViewNavigationProp = StackNavigationProp<
  ArticleNavigationParamList,
  "ArticleDetailImageViewScreen"
>;

export type CategoryParamList = {
  HomeCategorySelectScreen: undefined;
  CategoryHomeScreen: undefined;
  ArticleDetailScreen: undefined;
  ArticleFormScreen: undefined;
  ArticleDetailImageViewScreen: undefined;
};

export type CategoryHomeNavigationProp = StackNavigationProp<
  CategoryParamList,
  "CategoryHomeScreen"
>;

export type ArticleFormParamList = {
  ArticleFormScreen: undefined;
  ArticleContentsFormScreen: undefined;
  CategoryChoiceScreen: undefined;
};

export type ArticleFormScreenNavigationProp = StackNavigationProp<
  ArticleFormParamList,
  "ArticleFormScreen"
>;

export type ArticleContentsFormScreenNavigationProp = StackNavigationProp<
  ArticleFormParamList,
  "ArticleContentsFormScreen"
>;

export type Category =
  | "디지털/가전"
  | "가구/인테리어"
  | "유아동/유아도서"
  | "생활/가공식품"
  | "스포츠/레저"
  | "여성잡화"
  | "여성의류"
  | "남성패션/잡화"
  | "게임/취미"
  | "뷰티/미용"
  | "반려동물용품"
  | "도서/티켓/음반"
  | "기타 중고물품";

export type AuthorScoreType = 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9;

export interface ArticleDetailFavoriteProp {
  articleId: number;
}

export interface Feed {
  id: number;
  price: number;
  favoriteCount: number;
  tags: string[];
  favoriteState: boolean;
  photos: string[];
}

export type AppStackNavigationParamList = {
  TeaserScreen: undefined;
  AuthScreen: undefined;
  LoginScreen: undefined;
  JoinScreen: undefined;
  BottomTabNavigation: undefined;
};

export type TeaserScreenNavigationProp = StackNavigationProp<
  AppStackNavigationParamList,
  "LoginScreen"
>;

export type LoginScreenNavigationProp = StackNavigationProp<
  AppStackNavigationParamList,
  "LoginScreen"
>;

export type JoinScreenNavigationProp = StackNavigationProp<
  AppStackNavigationParamList,
  "JoinScreen"
>;

export type AuthScreenNavigationProp = StackNavigationProp<
  AppStackNavigationParamList,
  "AuthScreen"
>;

export interface Article {
  id: number;
  title: string;
  categoryName: string;
  price: number;
  contents: string;
  tradeState: string;
  tags: string[];
  photos: string[];
  author: Author;
  favoriteState: boolean;
  favoriteCount: number;
  hit: number;
  createdTime: string;
}

export interface ArticleCardProps {
  id: number;
  title: string;
  price: number;
  thumbnail: string;
  tradeState: string;
  favoriteState: boolean;
  favoriteCount: number;
  createdTime: string;
}

export interface Author {
  id: number;
  nickname: string;
  score: number;
  avatar: string;
  validated: boolean;
}

export interface Buyer {
  avatar: string;
  nickname: string;
}

export type ProfileNavigationParamList = {
  ProfileScreen: undefined;
  SalesHistoryScreen: undefined;
  PurchaseHistoryScreen: undefined;
  ArticleDetailScreen: undefined;
  ArticleDetailImageViewScreen: undefined;
  ArticleFormScreen: undefined;
  SelectBuyerScreen: undefined;
  EvaluationScreen: undefined;
  MyInfoScreen: undefined;
  MyFavoriteScreen: undefined;
  TeaserScreen: undefined;
};

export type ProfileScreenNavigationProp = StackNavigationProp<
  ProfileNavigationParamList,
  "ProfileScreen"
>;

export type SalesHistoryScreenNavigationProp = StackNavigationProp<
  ProfileNavigationParamList,
  "SalesHistoryScreen"
>;

export type PurchaseHistoryScreenNavigationProp = StackNavigationProp<
  ProfileNavigationParamList,
  "PurchaseHistoryScreen"
>;

export type SelectBuyerScreenNavigationProp = StackNavigationProp<
  ProfileNavigationParamList,
  "SelectBuyerScreen"
>;

export type MyInfoScreenNavigationProp = StackNavigationProp<
  ProfileNavigationParamList,
  "MyInfoScreen"
>;

export interface Score {
  questionId: number;
  score: number;
}

export interface Profile {
  nickname: string;
  avatar: string;
  score: number;
}
