export interface Order{
  skuCode: string,
  price: number,
  quantity: number
  userDetails?: UserDetails
}

export interface UserDetails{
  email:string;
  firstName: string,
  lastName: string
}
