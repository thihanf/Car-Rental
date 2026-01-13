let rowsToShow = 5;
let expanded = false;

document.addEventListener("DOMContentLoaded", () => {
  applyFilters();
});

/* MAIN FILTER FUNCTION */
function applyFilters() {
  const searchValue =
    document.getElementById("searchInput")?.value.toLowerCase() || "";

  const statusValue = document.getElementById("statusFilter")?.value || "";

  const rows = Array.from(document.querySelectorAll("#vehicleTable tr"));

  // Ignore "No vehicles found" row
  const dataRows = rows.filter((row) => row.querySelector("td"));

  // FILTERED ROWS
  const filteredRows = dataRows.filter((row) => {
    const rowText = row.textContent.toLowerCase();

    const statusBadge = row.querySelector("td:nth-child(5) span");
    const statusText = statusBadge
      ? statusBadge.innerText.trim().toUpperCase()
      : "";

    const matchesSearch = rowText.includes(searchValue);
    const matchesStatus = !statusValue || statusText === statusValue;

    return matchesSearch && matchesStatus;
  });

  // SHOW / HIDE ROWS BASED ON FILTER + PAGINATION
  dataRows.forEach((row, index) => {
    if (!filteredRows.includes(row)) {
      row.style.display = "none";
    } else {
      row.style.display = expanded || index < rowsToShow ? "" : "none";
    }
  });

  updateToggleButton(filteredRows.length);
}

/* SHOW MORE / SHOW LESS */
function toggleRows() {
  expanded = !expanded;
  applyFilters();
}

/* BUTTON STATE */
function updateToggleButton(visibleCount) {
  const btn = document.getElementById("toggleRowsBtn");
  if (!btn) return;

  if (visibleCount <= rowsToShow) {
    btn.style.display = "none";
  } else {
    btn.style.display = "inline-block";
    btn.textContent = expanded ? "Show Less" : "Show More";
  }
}
