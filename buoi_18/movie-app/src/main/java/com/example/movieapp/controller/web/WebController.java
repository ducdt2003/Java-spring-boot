package com.example.movieapp.controller.web;

import com.example.movieapp.entity.Movie;
import com.example.movieapp.model.enums.MovieType;
import com.example.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final MovieService movieService;

    @GetMapping("/")
    public String getHotMovies(Model model) {
        List<Movie> hotMovies = movieService.findHotMovie(true, 4);
        List<Movie> phimLeList = movieService.findByType(MovieType.PHIM_LE, true, 1, 6).getContent();
        List<Movie> phimBoList = movieService.findByType(MovieType.PHIM_BO, true, 1, 6).getContent();
        List<Movie> phimChieuRapList = movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, 1, 6).getContent();
        model.addAttribute("moviePage", hotMovies);
        model.addAttribute("phimLeList", phimLeList);
        model.addAttribute("phimBoList", phimBoList);
        model.addAttribute("phimChieuRapList", phimChieuRapList);
        return "index";
    }


    @GetMapping("/phim-bo")
    public String getPhimBoPage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "phim-bo";
    }
    @GetMapping("/phim-le")
    public String getPhimLePage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_LE, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "phim-chieu-rap";
    }

    /*    @GetMapping("/phim/{id}/{slug}")
        public String getMovieDetailsPage(@PathVariable Integer id, @PathVariable String slug) {
            return "chi-tiet-phim";
        }*/

    /*@GetMapping("/phim/{id}/{slug}")
    public String getMovieDetailsPage(@PathVariable Integer id, @PathVariable String slug, Model model) {
        // Gọi Service để lấy chi tiết phim
        Movie movie = movieService.findMovieDetails(id, slug);

        if (movie == null) {
            // Trả về trang lỗi nếu không tìm thấy phim
            return "error";
        }

        // Truyền thông tin phim vào Model để sử dụng trong View
        model.addAttribute("movie", movie);

        // Trả về file HTML để hiển thị
        return "chi-tiet-phim";
    }*/

  /*  @GetMapping("/phim/{id}/{slug}")
    public String getMovieDetailsPage(@PathVariable Integer id, @PathVariable String slug, Model model) {
        Movie movie = movieService.findMovieDetails(id, slug);
        if (movie == null) {
            return "error";
        }
        model.addAttribute("movie", movie);
        return "chi-tiet-phim";
    }*/

    @GetMapping("/phim/{id}/{slug}")
    public String getMovieDetailsPage(@PathVariable Integer id, @PathVariable String slug, Model model) {
        Movie movie = movieService.findMovieDetails(id, slug);
        if (movie == null) {
            return "error";
        }
        model.addAttribute("movie", movie);

        // Lấy danh sách phim liên quan
        List<Movie> relatedMovies = movieService.findRelatedMovies(movie.getType());
        model.addAttribute("relatedMovies", relatedMovies);

        return "chi-tiet-phim";
    }


   /* @GetMapping("/phim/{id}/{slug}")
    public String getMovieDetailsPage(@PathVariable Integer id, @PathVariable String slug, Model model) {
        Movie movie = movieService.findMovieDetails(id, slug);
        if (movie == null) {
            // Trả về trang lỗi nếu không tìm thấy phim
            return "error";
        }
        model.addAttribute("movie", movie);
        return "chi-tiet-phim";
    }*/

}
