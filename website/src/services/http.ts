import axios, { type AxiosInstance } from "axios";

export { instance };

const instance: AxiosInstance = axios.create({
  baseURL: "http://localhost:8080/api",
  withCredentials: true,
});
