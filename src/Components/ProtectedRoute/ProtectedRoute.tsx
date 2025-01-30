import { Navigate } from "react-router-dom";

interface ProtectedRouteProps {
  children: React.ReactNode;
}

const ProtectedRoute = ({ children }: ProtectedRouteProps) => {
  if(!localStorage.getItem("token")){
    return <Navigate to={"/login"}/>
  }
  return <>{children}</>;
};

export default ProtectedRoute;
