"use client";

import LoadingSpinner from "@/components/LoadingSpinner";
import { useRouter } from "next/navigation";
import { useEffect } from "react";

const App = () => {
  const router = useRouter();
  
  useEffect(() => {
    router.push("/join");
  }, []); 

  return <LoadingSpinner />;
};

export default App;