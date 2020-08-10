/**
 * @author kouz95
 */

import axios from "axios";

// const BASE_URL = "http://3.34.248.131:8080";
const BASE_URL = "http://localhost:8080";
const domain = {
  article: "/articles",
  tradeState: "/tradeState",
  members: "/members",
  login: "/login",
};

interface ArticlesPost {
  title: string;
  price: number;
  category: string;
  contents: string;
  tags: string[];
  photos: string[];
  authorId: number;
}

interface ArticlesGet {
  lastArticleId: number;
  size: number;
}

interface TradeStatePatch {
  id: number;
  tradeState: string;
}

export const articlesAPI = {
  get: async (params: ArticlesGet) =>
    await axios.get(BASE_URL + domain.article, { params }),
  post: async (data: ArticlesPost) =>
    await axios.post(BASE_URL + domain.article, data),
  patch: async (data: TradeStatePatch) =>
    await axios.patch(BASE_URL + domain.article + domain.tradeState, data),
};

export const articleDetailAPI = {
  get: async (articleId: number) =>
    await axios.get(BASE_URL + domain.article + "/" + articleId),
};

interface MemberLogin {
  email: string;
  password: string;
}

export const memberAPI = {
  post: async (data: MemberLogin) =>
    await axios.post(BASE_URL + domain.login, { data }),
};
