package kpi.ficting.kpitestplatform.util;

import kpi.ficting.kpitestplatform.dto.TestSessionDto;
import kpi.ficting.kpitestplatform.dto.TestSessionListDto;

import java.time.Duration;
import java.time.LocalDateTime;

public class CsvGeneratorUtils {
  private static final String CSV_HEADER = "Credentials,Mark,StartedAt,FinishedAt,Time\n";

  public static String generateCsv(TestSessionListDto testSessionListDto) {
    StringBuilder csvContent = new StringBuilder();
    csvContent.append(CSV_HEADER);
    for (TestSessionDto sessionListDto : testSessionListDto.getSessions()) {
      csvContent
          .append(String.format("%s %s",
              sessionListDto.getStudentGroup(), sessionListDto.getStudentName())).append(",")
          .append(sessionListDto.getMark()).append(",")
          .append(sessionListDto.getStartedAt()).append(",")
          .append(sessionListDto.getFinishedAt()).append(",")
          .append(formatDuration(sessionListDto.getStartedAt(), sessionListDto.getFinishedAt()))
          .append("\n");
    }
    return csvContent.toString();
  }

  private static String formatDuration(LocalDateTime start, LocalDateTime end) {
    if (start == null || end == null) {
      return "";
    }
    Duration duration = Duration.between(start, end);
    long hours = duration.toHours();
    long minutes = duration.toMinutes() % 60;
    long seconds = duration.getSeconds() % 60;
    return String.format("%02dh %02dm %02ds", hours, minutes, seconds);
  }
}
