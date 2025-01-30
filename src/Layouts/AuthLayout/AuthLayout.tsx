import { Outlet } from "react-router-dom";

const AuthLayout = () => {
  return (
    <section className="bg-gray-50 dark:bg-gray-900 min-h-dvh">
      <div className="py-8 px-4 mx-auto max-w-screen-xl lg:py-16 grid lg:grid-cols-2 gap-8 lg:gap-16">
        <div className="flex flex-col justify-center">
          <h1 className="mb-4 text-4xl font-extrabold tracking-tight leading-none text-gray-900 md:text-5xl lg:text-6xl dark:text-white">
            Organize Your Day, Your Way
          </h1>
          <p className="mb-6 text-lg font-normal text-gray-500 lg:text-xl dark:text-gray-400">
            Simplify your tasks and stay on top of your priorities with our
            intuitive To-Do App. Start building habits and achieving goals
            today.
          </p>
        </div>
        <Outlet />
      </div>
    </section>
  );
};

export default AuthLayout;
