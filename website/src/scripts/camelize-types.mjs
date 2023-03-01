#!/usr/bin/node

import fs from "fs/promises";
import humps from "humps";

async function camelize() {
  try {
    const path = "../lib/database.types.ts";
    const data = await fs.readFile(path);
    const text = data.toString();
    const camelizedText = text
      .split("\n")
      .map((line) => {
        const snakeCaseText = line.match(/[a-zA-Z]+(_[a-zA-Z]+)+/);
        if (snakeCaseText && snakeCaseText.length > 0) {
          return line.replace(
            /[a-zA-Z]+(_[a-zA-Z]+)+/,
            humps.camelize(snakeCaseText[0])
          );
        }
        return line;
      })
      .join("\n");
    await fs.writeFile(path, camelizedText);
  } catch (err) {
    console.log("Couldn't read file - doesn't exist?");
    return;
  }
}

await camelize();
