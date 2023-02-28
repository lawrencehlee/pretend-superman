import type { Ref } from "vue";
import { onMounted, ref, watch } from "vue";
import type { RouteLocation } from "vue-router";
import { useRoute } from "vue-router";
import { collapseToInt } from "@/lib/utils";

export default function useResourceByRouteId<Resource>(
  idFromRoute: (route: RouteLocation) => string | string[],
  resourceGetter: (id: number) => Promise<Resource | null>
): Ref<Resource | undefined> {
  const resource = ref<Resource>();
  const route = useRoute();

  onMounted(async () => {
    resource.value =
      (await resourceGetter(collapseToInt(idFromRoute(route)))) ?? undefined;
  });

  watch(
    () => idFromRoute(route),
    async (newId) => {
      resource.value =
        (await resourceGetter(collapseToInt(newId))) ?? undefined;
    }
  );

  return resource;
}
