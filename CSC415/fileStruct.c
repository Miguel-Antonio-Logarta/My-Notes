#include <time.h>
#include <stddef.h>

typedef struct {
  char[256] file_name;
  size_t location;
  size_t file_size;
  time_t date_created;
  time_t date_modified;
} directory_entry;
