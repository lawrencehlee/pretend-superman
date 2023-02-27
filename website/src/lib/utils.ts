/**
 * Collapses a string or string array to simply a string, using the first element in the array.
 * @param unknown
 */
export function collapseToString(unknown: string | string[]): string {
  if (unknown && typeof unknown === "object") {
    return unknown[0];
  }

  return unknown;
}

/**
 * Same as collapseToString, but also tries to parse to int
 */
export function collapseToInt(unknown: string | string[]): number {
  return parseInt(collapseToString(unknown));
}
