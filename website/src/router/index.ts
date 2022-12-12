import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import CommunitiesView from "@/views/CommunitiesView.vue";
import CommunityView from "@/views/CommunityView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/AboutView.vue"),
    },
    {
      path: "/communities",
      name: "communities",
      component: CommunitiesView,
    },
    {
      path: "/communities/:slug",
      name: "community",
      component: CommunityView,
    },
  ],
});

export default router;
