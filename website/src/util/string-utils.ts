export { stringOrStringArrayToString };

function stringOrStringArrayToString(unknown: string | string[]): string {
  if (unknown && typeof unknown === "object") {
    return unknown[0];
  }

  return unknown;
}
