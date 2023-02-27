import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import CommunitiesView from "@/views/CommunitiesView.vue";
import CommunityView from "@/views/CommunityView.vue";
import QueueView from "@/views/QueueView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/communities",
      name: "communities",
      component: CommunitiesView,
    },
    {
      path: "/communities/:communityId",
      name: "community",
      component: CommunityView,
    },
    {
      path: "/communities/:communityId/queues",
      redirect: (to) => {
        return {
          name: "community",
          params: to.params,
        };
      },
    },
    {
      path: "/communities/:communityId/queues/:queueId",
      name: "queue",
      component: QueueView,
    },
  ],
});

export default router;
