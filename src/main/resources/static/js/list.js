// tr 클릭 시 페이지 이동 처리
document.querySelectorAll('.table tr').forEach(tr => {
  tr.addEventListener('click', function() {
    const link = this.dataset.href;
    if (link) {
      location.href = link;
    }
  });
});

