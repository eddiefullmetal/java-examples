function (key, values) {
	var sum = 0;
	var rating = 0;
	
	for (var i = 0; i < values.length; i++){
		sum += values[i].count;
		rating += values[i].rating;
	}
	
	return {
		sum: sum,
		rating: rating/sum
	};
}