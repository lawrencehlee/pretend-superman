export { collapseToString };

/**
 * Collapses a string or string array to simply a string, using the first element in the array.
 * @param unknown
 */
function collapseToString(unknown: string | string[]): string {
  if (unknown && typeof unknown === "object") {
    return unknown[0];
  }

  return unknown;
}
