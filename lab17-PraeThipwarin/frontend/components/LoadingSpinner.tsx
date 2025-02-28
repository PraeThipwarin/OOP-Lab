const LoadingSpinner: React.FC = () => {
  return (
  <div className="absolute inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
    <div className="w-16 h-16 border-4 border-t-transparent border-blue-500 rounded-full animate-spin"></div>
  </div>
  );
};

export default LoadingSpinner;
