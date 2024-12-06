import request from "./request/request.ts";

export interface RequestOptions extends Record<string, any> {
  params?: Record<string, any>
  data?: Record<string, any>
}

export const get = <T = any>(url: string, options: RequestOptions = {}) => {
  return request.get<T, T>(url, options)
}

export const post = <T = any>(url: string, data?: any, options: RequestOptions = {}) => {
  return request.post<T, T>(url, data, options)
}

export const put = <T = any>(url: string, data?: any, options: RequestOptions = {}) => {
  return request.put<T, T>(url, data, options)
}

export const del = <T = any>(url: string, options: RequestOptions = {}) => {
  return request.delete<T, T>(url, options)
}