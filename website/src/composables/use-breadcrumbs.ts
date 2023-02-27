import type { Breadcrumb } from "@/services/breadcrumb";
import { type RouteLocation, useRoute } from "vue-router";
import type { Ref } from "vue";
import { computed } from "vue";

export default function useBreadcrumbs(): Ref<Breadcrumb[]> {
  const route = useRoute();
  return computed(() => fromRoute(route));
}

function fromRoute(route: RouteLocation): Breadcrumb[] {
  const sections = route.path.split("/");
  const breadcrumbs = [];
  let pathSoFar = "";
  for (const section of sections) {
    if (section.length === 0) {
      continue;
    }
    pathSoFar += `/${section}`;
    breadcrumbs.push({
      name: section,
      path: pathSoFar,
    });
  }

  return breadcrumbs;
}
