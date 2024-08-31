import { myAxios } from "./helper";

export const signUp = (user) => {
    return myAxios
        .post("/customer/add",user)
        .then((response) => response.data)
};

export const LogInUser = (data) => {
    return myAxios
        .post("/login",data)
        .then((response) => response.data)
};

export const RestaurantUserLogin = (data) => {
    return myAxios
        .post("/login/restaurant",data)
        .then((response) => response.data)
};

export const GetCartItems = () => {
    return myAxios
        .get("/cart/", {
            params: {
                token: localStorage.getItem('token'),
            },
        })
        .then((response) => response.data)
        .catch((error)=>{
                // console.log(error)
                console.log("fail");
        })
};

export const DeleteCartItem = (data) => {
    var url = '/cart/delete/'+data;
    return myAxios
        .delete(url, {
            params: {
                token: localStorage.getItem('token'),
            },
        })
        .then((response) => response.data)
};

export const DeleteUserCart = () => {
    return myAxios
        .delete("/cart/delete", {
            params: {
                token: localStorage.getItem('token'),
            },
        })
        .then((response) => response.data)
};