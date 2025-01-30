import { createBrowserRouter } from "react-router-dom";
import MainLayout from "../Layouts/MainLayout/MainLayout";
import Home from "../Components/Home/Home";
import SignUp from "../Components/Singup/SignUp";
import SignIn from "../Components/Signin/SignIn";
import ProtectedRoute from "../Components/ProtectedRoute/ProtectedRoute";
import AuthLayout from "../Layouts/AuthLayout/AuthLayout";

export const Routes = createBrowserRouter([
  {
    path: "",
    element: <MainLayout />,
    children: [
      {
        index: true,
        element: (
          <ProtectedRoute>
            <Home />
          </ProtectedRoute>
        ),
      },
    ],
  },
  {
    path: "",
    element: <AuthLayout />,
    children: [
      { path: "signup", element: <SignUp /> },
      { path: "login", element: <SignIn /> },
    ],
  },
]);
