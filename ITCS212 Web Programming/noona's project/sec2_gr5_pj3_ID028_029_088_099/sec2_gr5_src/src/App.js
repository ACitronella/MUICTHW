import {BrowserRouter, Routes, Route} from "react-router-dom";
import Nav from "./components/navbar.js";
import Home from './components/home.js';
import Footer from './components/footer.js';
import SignIn from './components/signin.js';
import Search from './components/search.js';
import AboutUs from './components/aboutus.js';
import Manage from './components/manage.js'
import ManageMovie from './components/movie_manage.js';
import ManageUser from './components/user_manage.js'
import MovieDetail from "./components/movie_detail.js";

function App() {
  return (
    <BrowserRouter> 
      <Nav />
      <Routes> 
        <Route path ="/" element = {<Home />} />
        <Route path ="/signin" element = {<SignIn />} />
        <Route path ="/search" element = {<Search />} />
        <Route path ="/signin" element = {<SignIn />} />
        <Route path ="/aboutus" element = {<AboutUs />} />
        <Route path ="/manage" element = {<Manage />} />
        <Route path ="/manage/movie" element = {<ManageMovie />} />
        <Route path ="/manage/user" element = {<ManageUser />} />
        <Route path ="/moviedetail/:id" element = {<MovieDetail />} />
      </Routes>
      <Footer /> 
    </BrowserRouter>
  );
}
export default App;
