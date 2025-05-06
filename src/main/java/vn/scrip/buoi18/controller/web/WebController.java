// WebController.java
package vn.scrip.buoi18.controller.web;
import org.springframework.web.bind.annotation.PathVariable;
import vn.scrip.buoi18.entity.Movie;
import vn.scrip.buoi18.model.enums.MovieType;
import vn.scrip.buoi18.service.MovieService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
@Controller
@RequiredArgsConstructor

public class WebController {
    private final MovieService movieService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        // Lấy danh sách các thể loại phim, phân trang cho từng thể loại
        List<Movie> hotMovies = movieService.findHotMovie(true, 4); // Ví dụ lấy phim hot
        model.addAttribute("hotMovies", hotMovies);
        return "index";  // Trang chủ
    }
    @GetMapping("/phim-le")
    public String getPhimLePage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        // Lấy danh sách phim lẻ với phân trang
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_LE, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "phim-le";
    }
    @GetMapping("/phim-bo")
    public String getPhimBoPage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        // Lấy danh sách phim bộ với phân trang
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "phim-bo";
    }
    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "18") Integer pageSize,
                                      Model model) {
        // Lấy danh sách phim chiếu rạp với phân trang
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "phim-chieu-rap";
    }

    @GetMapping("/phim/{id}/{slug}")
    public String getMovieDetailsPage(@PathVariable Integer id, @PathVariable String slug, Model model) {
        Movie movie = movieService.findMovieDetails(id, slug);
        model.addAttribute("movie", movie);
        return "chi-tiet-phim";
    }
}


