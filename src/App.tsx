import { RouterProvider } from "react-router-dom";
import { Routes } from "./lib/routes";
import { ToastContainer } from "react-toastify";

const App = () => {
  return (
    <>
      <RouterProvider router={Routes} />
      <ToastContainer />
    </>
  );
};

export default App;
